package com.example.starter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import java.util.List;

public class HelloReactiveVerticle extends AbstractVerticle {

  FtpService ftpService;
  MinIoService minIoService;

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloReactiveVerticle());
  }

  @Override
  public void start() {
    System.out.println("Hello, World!");
    ftpService = new FtpService();
    minIoService = new MinIoService(); // initialize this too
    ftpService.sayHello();
    processFiles("abc").subscribe(
      result -> System.out.println("✅ Finished! MinIO ID: " + result),
      error -> System.err.println("❌ Error during processing: " + error.getMessage())
    );
    ;
  }

  public Single<String> processFiles(String regex) {
    return searchMatchingFiles(regex)
      .flatMap(filePath -> downloadFile(filePath).toObservable()) // download as they're found
      .toList()  // wait for all to complete
      .flatMap(this::zipFiles)
      .flatMap(this::uploadToMinio);
  }

  private Observable<String> searchMatchingFiles(String regex) {
    System.out.println("searchMatchingFiles");
    // simulate async FTP search
    return Observable.create(emitter -> {
      System.out.println("hi here");
      List<String> matches = ftpService.searchFilesMatchingRegex(regex); // blocking in demo
      for (String match : matches) {
        emitter.onNext(match);
//        Thread.sleep(3000);
      }
      emitter.onComplete();
    });
  }

  private Single<byte[]> downloadFile(String path) {
    return Single.fromCallable(() -> ftpService.downloadFile(path)); // make it non-blocking in production
  }

  private Single<byte[]> zipFiles(List<byte[]> files) {
    return Single.fromCallable(() -> ZipUtils.zipFiles(files));
  }

  private Single<String> uploadToMinio(byte[] zipped) {
    return Single.fromCallable(() -> minIoService.upload("my-bucket", "result.zip", zipped));
  }
}

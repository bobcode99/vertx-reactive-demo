package com.example.starter;

import java.util.Arrays;
import java.util.List;


public class FtpService {

  public void sayHello(){
    System.out.println("hiii");
  }
  public List<String> searchFilesMatchingRegex(String regex) {
    System.out.println("call searchFilesMatchingRegex ");
    System.out.println("searchFilesMatchingRegex called with regex: " + regex);
    try {
      // Simulate time delay like real FTP search
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException("Search interrupted", e);
    }

    // Simulate found file paths
    return Arrays.asList("/remote/path/file1.txt", "/remote/path/file2.txt", "/remote/path/file3.txt");
  }

  public byte[] downloadFile(String path) {
    System.out.println("Downloading file: " + path);
    try {
      Thread.sleep(300); // Simulate download delay
    } catch (InterruptedException e) {
      throw new RuntimeException("Download interrupted", e);
    }
    return ("Data of " + path).getBytes();
  }
}

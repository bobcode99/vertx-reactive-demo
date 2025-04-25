package com.example.starter;


import java.util.List;

public class ZipUtils {
  public static byte[] zipFiles(List<byte[]> files) {
    System.out.println("Zipping " + files.size() + " files");
    try {
      Thread.sleep(400); // Simulate time to zip
    } catch (InterruptedException e) {
      throw new RuntimeException("Zip interrupted", e);
    }
    return "ZIPPED_CONTENT".getBytes(); // Mock zipped content
  }
}

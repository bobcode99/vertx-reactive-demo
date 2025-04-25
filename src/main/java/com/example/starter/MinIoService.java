package com.example.starter;

public class MinIoService {
  public String upload(String bucket, String name, byte[] content) {
    System.out.println("Uploading to MinIO: " + name + " (size: " + content.length + ")");
    try {
      Thread.sleep(200); // Simulate upload delay
    } catch (InterruptedException e) {
      throw new RuntimeException("Upload interrupted", e);
    }
    return "minio-object-id-12345"; // Mock ID
  }
}

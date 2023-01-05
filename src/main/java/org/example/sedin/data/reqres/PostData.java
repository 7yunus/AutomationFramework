package org.example.sedin.data.reqres;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostData {

  private final String name;
  private final String job;

  public PostData(final String name, final String job) {
    this.name = name;
    this.job = job;
  }
}
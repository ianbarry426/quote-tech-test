package com.tech.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

  private int id;
  private boolean dialogue;
  private List<String> tags;
  private String url;
  private int favourites_count;
  private int upvotes_count;
  private int downvotes_count;
  private String author;
  private String author_permalink;
  private String body;
}

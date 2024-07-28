package com.movieTicket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_info")
@Builder
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   movieId;
    private String title;
    private String language;
    private String votes;
    private String rating;
    private String genre;

    private String posterName;
    private String posterType;
    @Lob
    @Column(name = "poster",length = 1000)
    private byte[] poster;
}

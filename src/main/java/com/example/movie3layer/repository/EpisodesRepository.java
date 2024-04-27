package com.example.movie3layer.repository;

import com.example.movie3layer.model.Episodes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EpisodesRepository extends JpaRepository<Episodes, Integer> {
    @Modifying
    @Query(value = "INSERT INTO episodes (episodes_id, file_name, link, name, slug) VALUES (:id, :filename, :link, :name, :slug) " +
            "ON DUPLICATE KEY UPDATE file_name = VALUES(file_name), link = VALUES(link)", nativeQuery = true)
    void updateEpisodes(@Param("id") int id, @Param("filename") String filename, @Param("link") String link, @Param("name") String name, @Param("slug") String slug);
    @Modifying
    @Query(value = "DELETE movie_episodes, episodes FROM movie_episodes INNER JOIN episodes ON movie_episodes.episodes_id = episodes.episodes_id WHERE movie_episodes.movie_id = :id", nativeQuery = true)
    void deleteEpisodes(@Param("id") int id);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM episodes WHERE episodes_id = :id", nativeQuery = true)
    int checkEpisodeExists(@Param("id") int id);

}

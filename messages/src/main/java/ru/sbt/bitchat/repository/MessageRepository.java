package ru.sbt.bitchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sbt.bitchat.entity.MessageEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query(value = "select * from messages as m " +
            "where time <= (select time from messages where id = (:id)) " +
            "order by time desc limit (:n)", nativeQuery = true)
    List<MessageEntity> getByLastNBefore(@Param("id") Long id, @Param("n") int n);

    Optional<MessageEntity> findByIdempotenceId(UUID idempotenceId);
}

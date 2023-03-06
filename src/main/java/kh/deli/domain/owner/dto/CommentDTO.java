package kh.deli.domain.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    Integer cmt_seq;
    Integer board_seq;
    String cmt_contents;
    String cmt_writer;
    Timestamp cmt_write_time;
    String cmt_status;
    Integer cmt_parent_seq;
    Timestamp cmt_upd_time;
    String cmt_upd_contents;
}

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
public class BoardDTO {
    Integer b_seq;
    String b_title;
    String b_writer;
    String b_contents;
    Timestamp b_write_time;
    String b_category;
    Integer b_count;
    String b_status;
    Integer b_parent_seq;
    String b_upd_contents;
    Timestamp b_upd_time;
}

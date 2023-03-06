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
public class FileDTO {
    Integer f_seq;
    String f_oriname;
    String f_sysname;
    String f_path;
    String f_status;
    Timestamp f_upload_time;
    Integer board_seq;

}

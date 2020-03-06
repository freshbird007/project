package com.leaves.smalltiger.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Postings {
    private int conId;
    private String msgWords;
    private List<MultipartFile> files;
}

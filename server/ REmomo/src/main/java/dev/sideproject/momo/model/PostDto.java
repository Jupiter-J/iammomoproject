package dev.sideproject.momo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder //package-private 생성자가 자동으로 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만들어준다
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Data
public class PostDto {

    @ApiModelProperty(value = "고유 id")
    private Long id;

    @ApiModelProperty(value = "질문답변" , example = "오늘은 좋았어요")
    private String content;

    private Long userId;
    private Long topicId;

    @ApiModelProperty(value = "질문" , example = "기분이 어떤가요?")
    private String topicTitle;  //토픽으로 가져옴
    private Instant createAt;
//    private Instant updateAt;


}

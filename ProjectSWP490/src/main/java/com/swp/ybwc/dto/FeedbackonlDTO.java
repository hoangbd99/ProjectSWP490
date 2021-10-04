package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackonlDTO {
    private Long orderonlId;
    private Long grade;
    private String feedbackContent;

}

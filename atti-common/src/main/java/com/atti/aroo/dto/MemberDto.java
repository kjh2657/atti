package com.atti.aroo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @ApiModelProperty(example = "aroo")
    private String name;

    @ApiModelProperty(example = "2")
    private int age;

    @ApiModelProperty(example = "arooooo")
    private String userId;

}

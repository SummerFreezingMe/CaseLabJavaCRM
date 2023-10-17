package com.greenatom.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Описание модели \"Роль\"")
public class RoleDto {

    @Schema(description = "Название роли")
    private String name;
}
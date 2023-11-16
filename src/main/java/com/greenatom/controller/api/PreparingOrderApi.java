package com.greenatom.controller.api;

import com.greenatom.domain.dto.preparing_order.PreparingOrderResponseDTO;
import com.greenatom.exception.message.EmployeeErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "PreparingOrder API", description = "API для работы со сборкой заказа")
public interface PreparingOrderApi {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат заказов в обработке",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PreparingOrderResponseDTO.class)
                            )
                    }
            )
    })
    @Operation(
            summary = "Получение всех заказов по статусу"
    )
    ResponseEntity<List<PreparingOrderResponseDTO>> getPreparingOrders(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(defaultValue = "WAITING_FOR_PREPARING", required = false) String status);
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат сборки заказа",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PreparingOrderResponseDTO.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Сборки заказа по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeErrorMessage.class)
                            )
                    }
            )
    })
    ResponseEntity<PreparingOrderResponseDTO> findById(@Parameter(description = "Id сборки заказа") Long id);
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешное назначение сборщика"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Нет прав доступа",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeErrorMessage.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Сотрудника или сборки заказа по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(
            summary = "Назначение сборщика на заказ"
    )
    ResponseEntity<Void> appointCollector(@RequestParam(required = false) Long employeeId,
                                          @RequestParam Long preparingOrderId);


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешное завершение сборки заказа"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Нет прав доступа",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeErrorMessage.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Сотрудника или сборки заказа по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(
            summary = "Назначение сборщика на заказ"
    )
    ResponseEntity<Void> finishPreparingOrder(@Parameter(description = "Id сотрудника сборки заказов") Long employeeId,
                                              @Parameter(description = "Id сборки заказа") Long preparingOrderId);
}

package com.codegym.final_exam.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TransactionDTO {

    @NotNull
    private Long id;

    @NotNull
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải đúng định dạng (MGD-XXXX), trong đó XXXX là các chữ số (0-9)")
    private String transactionCode;

    @NotNull(message = "Tên khách hàng không được để trống")
    private String name;

    @NotNull
    @Future(message = "Ngày giao dịch phải là ngày/tháng/năm và phải lớn hơn thời gian hiện tại")
    private LocalDate transactionDate;

    @NotNull
    private String typeService;

    @NotNull
    @DecimalMin(value = "500000", message = "Đơn giá phải là số và phải lớn hơn 500.000 (VND)")
    private double price;

    @NotNull
    @DecimalMin(value = "20", message = "Diện tích phải là số và lớn hơn 20 (m2)")
    private double area;
}

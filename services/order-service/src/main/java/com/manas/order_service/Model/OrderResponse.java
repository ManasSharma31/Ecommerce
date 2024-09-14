package com.manas.order_service.Model;

import java.math.*;
import com.manas.order_service.Entity.PayMethodMode;

public record OrderResponse(
     Integer id,
     String reference,
     BigDecimal totalPrice,
     String customerId,
     PayMethodMode payMethodMode) {

}

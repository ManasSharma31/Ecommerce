package com.manas.customerservice.Model;
import java.util.*;

public record ErrorResponse(Map<String,String> errors) {
}

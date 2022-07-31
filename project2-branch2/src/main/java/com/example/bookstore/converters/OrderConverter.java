package com.example.bookstore.converters;

import com.example.bookstore.DTOs.order.OrderDto;
import com.example.bookstore.entities.Order;
import org.modelmapper.ModelMapper;

public class OrderConverter {
    public static OrderDto convertOrderToDto(Order order){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(order, OrderDto.class);
    }
    public static Order convertOrderDtoToEntity(OrderDto orderDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderDto,Order.class);
    }
}

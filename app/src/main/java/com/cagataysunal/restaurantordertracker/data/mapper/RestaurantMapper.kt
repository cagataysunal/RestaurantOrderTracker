package com.cagataysunal.restaurantordertracker.data.mapper

import com.cagataysunal.restaurantordertracker.data.dto.RestaurantDto
import com.cagataysunal.restaurantordertracker.domain.model.Restaurant

fun RestaurantDto.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        email = email,
        phone = phone,
        address = "$physicalAddress, $district, $city, $country",
        operatingHours = "$operationStartTime - $operationEndTime",
        description = description,
        qrCodeUrl = qrCode
    )
}

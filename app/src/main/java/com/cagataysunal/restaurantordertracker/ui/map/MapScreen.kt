@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.cagataysunal.restaurantordertracker.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.Marker

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(lat: Double, lon: Double) {
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(lon, lat))
            zoom(14.0)
            pitch(0.0)
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MapboxMap(
                modifier = Modifier.fillMaxSize(),
                mapViewportState = mapViewportState,
            ) {
                Marker(point = Point.fromLngLat(lon, lat))
            }
        }
    }
}

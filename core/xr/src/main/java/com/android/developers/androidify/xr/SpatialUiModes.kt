/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.developers.androidify.xr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.xr.scenecore.scene

/** Check if the device is XR-enabled, but is not yet rendering spatial UI. */
@Composable
fun couldRequestFullSpace(): Boolean {
    return LocalSpatialConfiguration.current.hasXrSpatialFeature && !LocalSpatialCapabilities.current.isSpatialUiEnabled
}

/** Check if the device is XR-enabled and is rendering spatial UI. */
@Composable
fun couldRequestHomeSpace(): Boolean {
    return LocalSpatialConfiguration.current.hasXrSpatialFeature && LocalSpatialCapabilities.current.isSpatialUiEnabled
}

/** Default styling for an IconButton with a home space button and behavior. */
@Composable
fun RequestHomeSpaceIconButton(
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
) {
    val session = LocalSession.current ?: return

    IconButton(
        modifier = modifier,
        colors = colors,
        onClick = {
            session.scene.requestHomeSpaceMode()
        },
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            imageVector = ImageVector.vectorResource(R.drawable.collapse_content_24px),
            contentDescription = stringResource(R.string.xr_to_home_space_mode),
        )
    }
}

/** Default styling for an IconButton with a full space button and behavior. */
@Composable
fun RequestFullSpaceIconButton(
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
) {
    val session = LocalSession.current ?: return

    IconButton(
        modifier = modifier,
        colors = colors,
        onClick = {
            session.scene.requestFullSpaceMode()
        },
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            imageVector = ImageVector.vectorResource(R.drawable.expand_content_24px),
            contentDescription = stringResource(R.string.xr_to_full_space_mode),
        )
    }
}

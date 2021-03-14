/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge.ui

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

interface Images {
    val title: String
    val resId: Int
}

enum class FavoriteImages(override val title: String, @DrawableRes override val resId: Int) : Images {
    A("Short mantras", R.drawable.short_mantras),
    B("Nature meditations", R.drawable.nature_meditations),

    C("Stress and anxiety", R.drawable.stress_and_anxiety),
    D("Self-massage", R.drawable.self_massage),

    E("Overwhelmed", R.drawable.overwhelmed),
    F("Nightly wind down", R.drawable.nightly_wind_down)
}

enum class BodyImages(override val title: String, @DrawableRes override val resId: Int) : Images {
    A("Inversion", R.drawable.inversions),
    B("Quick yoga", R.drawable.quick_yoda),
    C("Stretching", R.drawable.stretching),
    D("Tabata", R.drawable.tabata),
    E("HIIT", R.drawable.hiit),
    F("Pre-natal yoga", R.drawable.pre_natal_yoga)
}

enum class MindImages(override val title: String, @DrawableRes override val resId: Int) : Images {
    A("Meditate", R.drawable.meditate),
    B("With kids", R.drawable.with_kids),
    C("Aromatherapy", R.drawable.aromatherapy),
    D("On the go", R.drawable.on_the_go),
    E("With pets", R.drawable.with_pets),
    F("High stress", R.drawable.high_stress)
}

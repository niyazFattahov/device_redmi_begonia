/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redmi.xiaomiparts;

import android.media.audiofx.AudioEffect;

import java.util.UUID;

class MiSound extends AudioEffect {

    private static final int MISOUND_PARAM_HEADSET_TYPE = 1;
    private static final int MISOUND_PARAM_EQ_LEVEL = 2;
    private static final int MISOUND_PARAM_MUSIC = 4;

    private static final UUID EFFECT_TYPE_MISOUND =
            UUID.fromString("5b8e36a5-144a-4c38-b1d7-0002a5d5c51b");

    MiSound(int priority, int audioSession) {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_MISOUND, priority, audioSession);
    }

    int getMusic() throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] value = new int[1];
        checkStatus(getParameter(MISOUND_PARAM_MUSIC, value));
        return value[0];
    }

    void setMusic(int enable) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(MISOUND_PARAM_MUSIC, enable));
    }

    int getHeadsetType() throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] value = new int[1];
        checkStatus(getParameter(MISOUND_PARAM_HEADSET_TYPE, value));
        return value[0];
    }

    void setHeadsetType(int type) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(MISOUND_PARAM_HEADSET_TYPE, type));
    }

    void setLevel(int band, float level) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(new int[]{MISOUND_PARAM_EQ_LEVEL, band},
                String.valueOf(level).getBytes()));
    }

    float getLevel(int band) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] param = new int[2];
        byte[] value = new byte[10];
        param[0] = MISOUND_PARAM_EQ_LEVEL;
        param[1] = band;
        checkStatus(getParameter(param, value));
        return Float.valueOf(new String(value));
    }
}

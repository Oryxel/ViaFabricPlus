/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD
 * Copyright (C) 2023      RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.viafabricplus.protocolhack.util;

import io.netty.channel.local.LocalChannel;

/**
 * This class can be used when creating dummy {@link com.viaversion.viaversion.api.connection.UserConnection} instances.
 * This class will prevent packets created by ViaVersion from being sent to the server. This is mainly used in the
 * translator system. See {@link de.florianmichael.viafabricplus.protocolhack.translator} for more information.
 */
public class NoPacketSendChannel extends LocalChannel {

    public static final NoPacketSendChannel INSTANCE = new NoPacketSendChannel();

    private NoPacketSendChannel() {
    }

}
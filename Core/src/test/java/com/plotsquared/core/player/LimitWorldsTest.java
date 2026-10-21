/*
 * PlotSquared, a land and world management plugin for Minecraft.
 * Copyright (C) IntellectualSites <https://intellectualsites.com>
 * Copyright (C) IntellectualSites team and contributors
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.plotsquared.core.player;

import com.plotsquared.core.configuration.Config;
import com.plotsquared.core.configuration.Settings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LimitWorldsTest {

    @AfterEach
    void reset() {
        Settings.LIMIT_GROUPS = null;
    }

    @Test
    void groupedWorldSharesLimitWithGroup() {
        Settings.Limit_Groups group = new Settings.Limit_Groups();
        group.WORLDS = List.of("normal", "large", "huge");
        Settings.LIMIT_GROUPS = new Config.ConfigBlock<>();
        Settings.LIMIT_GROUPS.put("default", group);

        assertEquals(List.of("normal", "large", "huge"), PlotPlayer.getLimitWorlds("large"));
        assertEquals(List.of("event"), PlotPlayer.getLimitWorlds("event"));
    }

    @Test
    void noGroupsMeansOwnWorldOnly() {
        assertEquals(List.of("normal"), PlotPlayer.getLimitWorlds("normal"));
    }

}

package fr.robotv2.akinaorewards.quest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Quest {
    TIME_PRACTICE("&8» &fJouer 30 minutes en: &ePractice", Arrays.asList("&e+500 pièces", "&c+1 AkinaoPoint"), 30),
    TIME_DAC("&8» &fJouer 30 minutes en: &eDé-à-Coudre", Arrays.asList("&e+1000 pièces", "&c+1 AkinaoPoint"), 30),
    TIME_PVP_SWAP("&8» &fJouer 30 minutes en: &ePvP-Swap", Arrays.asList("&e+1000 pièces", "&c+1 AkinaoPoint"), 30),
    TIME_DEMON_SLAYER_ADVENTURE("&8» &fJouer 30 minutes en: &eDemon Slayer Adventure", Arrays.asList("&e+1000 pièces", "&c+1 AkinaoPoint"), 30),

    PLAYED_DAC_1("&8» &fJouer 1 partie en: &eDé-à-Coudre", Arrays.asList("&e+300 pièces"), 1),
    PLAYED_DAC_2("&8» &fJouer 2 parties en: &eDé-à-Coudre", Arrays.asList("&e+500 pièces", "&c+1 AkinaoPoint"), 2),
    PLAYED_DAC_3("&8» &fJouer 3 parties en: &eDé-à-Coudre", Arrays.asList("&e+500 pièces", "&c+1 AkinaoPoint"), 3),

    PLAYED_PVP_SWAP_1("&8» &fJouer 1 partie en: &ePvP-Swap", Arrays.asList("&e+300 pièces"), 1),
    PLAYED_PVP_SWAP_2("&8» &fJouer 2 parties en: &ePvP-Swap", Arrays.asList("&e+500 pièces", "&c+1 AkinaoPoint"), 2),
    PLAYED_PVP_SWAP_3("&8» &fJouer 3 parties en: &ePvP-Swap", Arrays.asList("&e+1000 pièces", "&c+5 AkinaoPoint"), 3),

    PLAYED_DEMON_SLAYER_UHC("&8» &fJouer 1 partie en: &eDemon Slayer UHC", Arrays.asList("&e+500 pièces", "&c+1 AkinaoPoint"), 1);

    private final int id;
    private final String title;
    private final List<String> rewards;
    private final int targetValue;
    Quest(String title, List<String> rewards, int value) {
        Objects.requireNonNull(title);
        this.id = this.ordinal() + 1;
        this.title = title;
        this.rewards = rewards;
        this.targetValue = value;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getRewards() {
        return rewards;
    }

    public int getTargetValue() {
        return targetValue;
    }
}

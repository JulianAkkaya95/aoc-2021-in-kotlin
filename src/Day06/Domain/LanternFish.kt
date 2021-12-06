package Day06.Domain

import java.util.*

class LanternFish(var daysUntilNewFish: Int) {

    constructor() : this(8)

    fun newDay(): Optional<LanternFish> {
        var newFish: Optional<LanternFish> = Optional.empty()

        if (daysUntilNewFish == 0) {
            newFish =  Optional.of(LanternFish())
            daysUntilNewFish = 6
        } else {
            daysUntilNewFish--
        }

        return newFish
    }
}
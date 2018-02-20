package io.github.yusukeiwaki.bottomsheetbehaviorbug

import android.support.design.widget.BottomSheetBehavior

internal class BottomSheetHideableHelper(private val bottomSheet: BottomSheetBehavior<*>) {
    private var hideable: Boolean = false

    fun setHideable(hideable: Boolean) {
        this.hideable = hideable

        if (!hideable) {
            // stateがhiddenじゃなければ、即座にhideable=falseにする
            if (bottomSheet.state != BottomSheetBehavior.STATE_HIDDEN && bottomSheet.isHideable) {
                bottomSheet.isHideable = false
            }
        } else {
            // hideable=trueはいつでもOK
            bottomSheet.isHideable = true
        }
    }

    fun onStateChanged(newState: Int) {
        // state がhiddenじゃなくなったのを待ち合わせてからhideable=falseにする
        if (newState != BottomSheetBehavior.STATE_HIDDEN && !hideable && bottomSheet.isHideable) {
            bottomSheet.isHideable = false
        }
    }
}

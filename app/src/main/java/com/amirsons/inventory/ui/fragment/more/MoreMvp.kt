package com.amirsons.inventory.ui.fragment.more

/* Created by Imran Khan on 16-Jun-19.
 * Copyright (c) Imran Khan All rights reserved.*/

import com.amirsons.inventory.ui.base.BasePresenter
import com.amirsons.inventory.ui.base.BaseView

internal interface MoreView : BaseView

internal interface MorePresenter : BasePresenter

class MoreMvp internal constructor(private val mMoreView: MoreView) : MorePresenter

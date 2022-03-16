//
//  ViewExt.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/16.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension View {
    
    @ViewBuilder
    func isHidden(_ isHidden: Bool) -> some View {
        if isHidden {
            self.hidden()
        } else {
            self
        }
    }
}

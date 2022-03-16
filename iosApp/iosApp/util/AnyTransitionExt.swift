//
//  AnyTransitionExt.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/16.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension AnyTransition {
    static var slideToLeftTransition: AnyTransition {
        .modifier(active: MySlideViewModifier(x: 0), identity: MySlideViewModifier(x: -(UIScreen.main.bounds.width)))
    }
        
    static var slideToRightTransition: AnyTransition {
        .modifier(active: MySlideViewModifier(x: 0), identity: MySlideViewModifier(x: UIScreen.main.bounds.width))
    }
}

struct MySlideViewModifier : ViewModifier {
    let x: CGFloat
    
    func body(content: Content) -> some View {
        content.offset(x: x, y: 0)
    }
}

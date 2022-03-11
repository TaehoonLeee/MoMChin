//
//  BungView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BungView : View {
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, MoMChinMainBungChild>>
    
    init(_ component: MoMChinMain) {
        self.routerState = ObservableValue(component.bungRouterState)
    }
    
    var body: some View {
        let child = routerState.value.activeChild.instance
        
        switch child {
        case let list as MoMChinMainBungChildList:
            Text("Bung")
        case let detail as MoMChinMainBungChildDetail:
            EmptyView()
        default: EmptyView()
        }
    }
}

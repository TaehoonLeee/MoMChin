//
//  MessageView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MessageView : View {
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, MoMChinMainMessageChild>>
    
    init(_ component: MoMChinMain) {
        self.routerState = ObservableValue(component.messageRouterState)
    }
    
    var body: some View {
        let child = routerState.value.activeChild.instance
        
        switch child {
        case let list as MoMChinMainCommunityChildList:
            Text("Message")
        case let detail as MoMChinMainCommunityChildDetail:
            EmptyView()
        default: EmptyView()
        }
    }
}

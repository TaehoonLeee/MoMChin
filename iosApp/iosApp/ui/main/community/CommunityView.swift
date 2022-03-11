//
//  CommunityView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct CommunityView : View {
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, MoMChinMainCommunityChild>>
    
    init(_ component: MoMChinMain) {
        self.routerState = ObservableValue(component.communityRouterState)
    }
    
    var body: some View {
        let child = routerState.value.activeChild.instance
        
        switch child {
        case let list as MoMChinMainCommunityChildList:
            CommunityListView(list.component)
        case let detail as MoMChinMainCommunityChildDetail:
            CommunityDetailView(detail.component)
        default: EmptyView()
        }
    }
}

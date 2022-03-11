//
//  RootView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct RootView : View {
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, MoMChinRootChild>>
    
    init(_ component: MoMChinRoot) {
        self.routerState = ObservableValue(component.routerState)
    }
    
    var body: some View {
        let child = routerState.value.activeChild.instance
        
        switch child {
        case let login as MoMChinRootChildLogin:
            LoginView(login.component)
        case let main as MoMChinRootChildMain:
            MainView(main.component)
        default: EmptyView()
        }
    }
}

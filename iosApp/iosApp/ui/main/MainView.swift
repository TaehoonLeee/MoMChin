//
//  MainView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainView : View {
    
    private let component: MoMChinMain
    
    init(_ component: MoMChinMain) {
        self.component = component
        UITabBar.appearance().backgroundColor = UIColor.white
    }
    
    var body: some View {
        TabView {
            CommunityView(component) { child, isHidden in
                if child is MoMChinMainCommunityChildList {
                    CommunityListView((child as! MoMChinMainCommunityChildList).component)
                        .isHidden(isHidden)
                        .zIndex(1)
                }
                
                if child is MoMChinMainCommunityChildDetail {
                    CommunityDetailView((child as! MoMChinMainCommunityChildDetail).component)
                        .isHidden(isHidden)
                        .zIndex(2)
                }
            }.tabItem {
                Image(systemName: "1.square.fill")
                Text("Community")
            }

            BungView(component)
                .tabItem {
                    Image(systemName: "2.square.fill")
                    Text("Bung")
                }

            MessageView(component)
                .tabItem {
                    Image(systemName: "3.square.fill")
                    Text("Message")
                }
        }
    }
}

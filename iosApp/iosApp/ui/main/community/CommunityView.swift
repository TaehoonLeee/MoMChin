//
//  CommunityView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct CommunityView<Content: View> : View {
    
    @ObservedObject
    private var routerState: ObservableValue<NSArray>
    private let render: (MoMChinMainCommunityChild, _ isHidden: Bool) -> Content
    
    init(_ component: MoMChinMain, @ViewBuilder render: @escaping (MoMChinMainCommunityChild, _ isHidden: Bool) -> Content) {
        self.routerState = ObservableValue(component.animatedCRouterState)
        self.render = render
    }
    
    var body: some View {
        let routerState = routerState.value
        let children = routerState.compactMap { $0 as? AnimatedChild<MoMChinMainCommunityChild> }.map { $0 }
        
        return ZStack {
            ForEach(children.indices, id: \.hashValue) { idx in
                let item = children[idx]
                
                if item.state == StackState.firstBackStack {
                    render(item.child!, true)
                        .transition(AnyTransition.asymmetric(insertion: .slideToLeftTransition, removal: AnyTransition.identity))
                        .animation(.default)
                }
                
                if item.state == StackState.active {
                    let _ = print(item.entrance)
                    if item.entrance == AnimatedDirection.left {
                        self.render(item.child!, false)
                            .transition(AnyTransition.asymmetric(insertion: .move(edge: .leading), removal: AnyTransition.identity))
                            .animation(.default)
                    } else if item.entrance == AnimatedDirection.right{
                        self.render(item.child!, false)
                            .transition(AnyTransition.asymmetric(insertion: .move(edge: .trailing), removal: AnyTransition.identity))
                            .animation(.default)
                    } else {
                        self.render(item.child!, false)
                    }
                }
                
                if item.state == StackState.popped {
                    self.render(item.child!, true)
                        .transition(AnyTransition.asymmetric(insertion: .slideToRightTransition, removal: AnyTransition.identity))
                        .animation(.default)
                }
            }
        }
    }
}

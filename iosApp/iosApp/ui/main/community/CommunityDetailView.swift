//
//  CommunityDetailView.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct CommunityDetailView : View {
    
    private let component: CommunityDetail
    
    @ObservedObject
    private var model: ObservableValue<CommunityDetailModel>
    
    init(_ component: CommunityDetail) {
        self.component = component
        self.model = ObservableValue(component.model)
    }
    
    var body: some View {
        let model = model.value
        let content = model.detail
        
        NavigationView {
            VStack {
                VStack {
                    Text(content.title)
                    Text(content.user)
                    Text(content.content)
                }
                
                ScrollView {
                    LazyVStack(alignment: .center, spacing: 12) {
                        let uniqueItems = model.comments.map { Unique($0) }
                        
                        ForEach(uniqueItems) { uniqueItem in
                            let comment = uniqueItem.value
                            
                            VStack {
                                Text(comment.writer)
                                Text(comment.content)
                            }
                        }
                    }
                }
            }
        }
    }
}

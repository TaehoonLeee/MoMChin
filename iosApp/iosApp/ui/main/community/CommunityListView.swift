//
//  CommunityListView.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct CommunityListView : View {
    
    private let component: CommunityList
    
    @ObservedObject
    private var model: ObservableValue<CommunityListModel>
    
    init(_ component: CommunityList) {
        self.component = component
        self.model = ObservableValue(component.model)
    }
    
    var body: some View {
        let model = model.value
        
        NavigationView {
            VStack {
                HStack {
                    ForEach(model.categories, id: \.self) {
                        Text($0)
                    }
                }
                
                ScrollView {
                    LazyVStack(alignment: .center, spacing: 12) {
                        let uniqueItems = model.items.map { Unique($0) }
                        
                        ForEach(uniqueItems) { uniqueItem in
                            let item = uniqueItem.value
                            VStack {
                                Text(item.title)
                                Text(item.user)
                                Text(item.content)
                            }.onTapGesture {
                                component.onItemClicked(item: item, category: model.categories[0])
                            }
                        }
                    }
                }
            }
        }
    }
}

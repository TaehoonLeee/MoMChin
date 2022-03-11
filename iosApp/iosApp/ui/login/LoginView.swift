//
//  LoginView.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView : View {
    
    private let component: MoMChinLogin
    
    init(_ component: MoMChinLogin) {
        self.component = component
    }
    
    var body: some View {
        VStack {
            Button(action: component.onLogin) {
                Text("Naver")
            }
            Button(action: component.onLogin) {
                Text("Kakao")
            }
            Button(action: component.onLogin) {
                Text("Google")
            }
            Button(action: component.onLogin) {
                Text("Apple")
            }
        }
    }
}

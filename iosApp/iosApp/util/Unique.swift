//
//  Unique.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

struct Unique<T> : Identifiable {
    let value: T
    let id = UUID()
    
    init(_ value: T) {
        self.value = value
    }
}

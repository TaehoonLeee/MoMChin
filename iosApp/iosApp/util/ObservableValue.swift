//
//  ObservableValue.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/11.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import Combine

class ObservableValue<T: AnyObject> : ObservableObject {
    
    private let observableValue: Value<T>
    
    @Published
    var value: T
    
    private var observer: ((T) -> Void)?
    
    init(_ value: Value<T>) {
        self.observableValue = value
        self.value = observableValue.value
        self.observer = { self.value = $0 }
        
        observableValue.subscribe(observer: observer!)
    }
    
    deinit {
        self.observableValue.unsubscribe(observer: observer!)
    }
}

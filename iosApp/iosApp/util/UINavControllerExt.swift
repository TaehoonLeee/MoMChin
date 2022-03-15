//
//  UINavControllerExt.swift
//  iosApp
//
//  Created by taehoon lee on 2022/03/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import UIKit

extension UINavigationController: ObservableObject, UIGestureRecognizerDelegate {
    open override func viewDidLoad() {
        super.viewDidLoad()
        navigationBar.isHidden = true
        interactivePopGestureRecognizer?.delegate = self
    }
    
    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return viewControllers.count > 1
    }
}

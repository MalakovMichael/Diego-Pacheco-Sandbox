//
//  main.m
//  objectivec.playground
//
//  Created by Dev on 4/14/14.
//  Copyright (c) 2014 Dev. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Person.h"
#import "BasicMath.h"

int main(int argc, const char * argv[]){
    @autoreleasepool {
        
        BasicMath *math = [[BasicMath alloc] init];
        [math doItAll];
        
        Person *diego = [[Person alloc] init];
        [diego doItAll];
        
    }
    return 0;
}
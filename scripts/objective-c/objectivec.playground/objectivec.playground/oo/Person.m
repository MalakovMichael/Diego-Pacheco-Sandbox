//
//  Person.m
//  objectivec_cli_playground
//
//  Created by Dev on 4/11/14.
//  Copyright (c) 2014 Dev. All rights reserved.
//

#import "Person.h"

@implementation Person

- (void)sayHi {
    NSLog(@"Hello %@.", self.name);
}

- (void)doItAll{
    Person *diego = [[Person alloc] init];
    
    [diego setName: @"Diego"];
    NSLog(@"Created a %@", [diego name]);
    
    diego.name = @"Diego Pacheco";
    NSLog(@"Changed the PERSON to a %@", diego.name);
    
    [diego sayHi];
    
    [diego setLastName: @"Pacheco"];
     NSLog(@"%@", [diego lastName]);
    
}

@end
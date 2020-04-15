
'use strict';

const clearColor = new ClearColor(1.0, 0.5, 0.25, 0.1);

console.log('Clear color red:   ' + clearColor.r);
console.log('Clear color green: ' + clearColor.g);
console.log('Clear color blue:  ' + clearColor.b);
console.log('Clear color alpha: ' + clearColor.a);
console.log('Alternative alpha: ' + clearColor.alphaFromOpaqueInternal());

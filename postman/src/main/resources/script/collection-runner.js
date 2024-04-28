"use strict";

const newman = require('newman');

console.log('Current directory: ' + process.cwd());

console.log("node: " + process.argv[0])
console.log("js: " + process.argv[1])
var json = unescape(process.argv[2]).replace(new RegExp('<>', 'g'), '"')
console.log("json: " + json)
//options = JSON.parse('{"collection":"sample-collection.json"}');
//console.log("parsed json: " + JSON.stringify(options))

var config = JSON.parse(json);

var run = newman
.run(config)
.on('start', function (err, args) { // on start of run, log to console
    console.log('running a collection...');
}).on('done', function (err, summary) {
   if (err) {
          console.log(JSON.stringify(err, null, 1));
          process.exit(2);
   } else {
       if (summary.run.failures && summary.run.failures.length > 0) {
           process.exit(1);
       } else {
          console.log('collection run complete!');
          process.exit(0);
       }
   }
});
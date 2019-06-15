const {
    cli,
    prefixTransform,
    rawArgs,
    sh,
} = require('tasksfile')

function hello(options) {
    console.log('OPTIONS', options)
    console.log('RAW ARGS', rawArgs())
}

function buildjs(options, infile, outfile='greeter.js') {
    sh(`tsc --outFile "${outfile}" "${infile}"`)
}

function open(options) {
    sh('open greeter.html')
}

cli({
    buildjs,
    hello,
    open,
})

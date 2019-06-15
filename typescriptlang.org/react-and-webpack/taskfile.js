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

function webpack() {
    sh('webpack ' + rawArgs().join(' '))
}

function open() {
    webpack()
    sh('open index.html')
}

cli({
    hello,
    open,
    webpack,
})

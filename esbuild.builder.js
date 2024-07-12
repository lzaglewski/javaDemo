const esbuild = require('esbuild');
const manifestPlugin = require('esbuild-plugin-manifest')

const config = {
    entryPoints: [
        'assets/admin/js/admin.js',
        'assets/admin/js/admin.vendor.js'
    ],
    bundle: true,
    outdir: 'src/main/resources/static/dist',
    outbase: '.',
    // plugins: [manifestPlugin()],
    loader: {
        '.woff2': 'file',
        '.ttf': 'file',
    }
};

if (process.argv[2] === 'build') {
    console.log('building...')
    esbuild.build(config).catch((e) => console.error(e.message))
}

if (process.argv[2] === 'watch') {
    console.log('watching...')
    esbuild
        .context(config)
        .then(ctx => ctx.watch());
}

if (process.argv[2] === 'prod') {
    console.log('building...')
    esbuild
        .build(Object.assign(config, {
            minify: true,
            sourcemap: true,
            target: [
                'chrome58',
                'firefox57',
                'edge18',
                'safari11'
            ]
        }))
        .catch((e) => console.error(e.message))
}

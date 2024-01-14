module.exports = {
    preset: 'ts-jest',
    setupFilesAfterEnv: ['<rootDir>/src/setupTests.ts'],
    testMatch: ['<rootDir>/src/**/*.spec.{ts,tsx}'],
    testEnvironment: 'jest-environment-jsdom',
    clearMocks: true,
    transform: {
        '^.+\\.(js|jsx|ts|tsx)$': 'babel-jest',
        '^.+\\.css$': '<rootDir>/config/jest/cssTransform.js',
        '^(?!.*\\.(js|jsx|ts|tsx|css|json)$)':
            '<rootDir>/config/jest/fileTransform.js'
    },
    transformIgnorePatterns: [
        '^.+\\.module\\.(css|sass|scss)$'
    ],
    modulePaths: [],
    moduleNameMapper: {
        '^react-native$': 'react-native-web',
        '^.+\\.module\\.(css|sass|scss)$': 'identity-obj-proxy',
        '\\.svg': '<rootDir>/config/jest/svgrMock.js'
    },
    moduleFileExtensions: [
        'web.js',
        'js',
        'web.ts',
        'ts',
        'web.tsx',
        'tsx',
        'json',
        'web.jsx',
        'jsx',
        'node'
    ]
};

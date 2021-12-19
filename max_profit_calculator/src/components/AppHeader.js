import React from 'react';
import { Layout } from "antd";

const { Header } = Layout;

const AppHeader = () => {

    return (
        <Header
            style={{
                height: "200px",
                textAlign: "center",
                padding: "30px",
                color: "#fafafa",
                backgroundImage: "radial-gradient(circle, #1890ff 0%, #e6f7ff 100%)",
                borderShadow: "0 2px 8px #f0f1f2"
            }}
        >
            <h1 style={{ fontWeight: 700, fontSize: "50px", color: "#434343", margin: "50px auto" }}>
                Max Profit Calculator
            </h1>
            
        </Header>
    );
}

export default AppHeader;